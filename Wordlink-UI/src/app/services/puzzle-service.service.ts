import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import PuzzleInfo from '../PuzzleInfo';
import { httpUrl } from 'src/environments/environment';
import { PriorPuzzleInfo } from '../components/PriorPuzzleInfo';

@Injectable({
  providedIn: 'root'
})
export class PuzzleServiceService {

  private puzzleUrl = '/puzzle';

  constructor(private http: HttpClient) { }

  getPuzzleInfo(): Observable<PuzzleInfo> {
    return this.http.get<PuzzleInfo>(httpUrl + this.puzzleUrl);
  }

  validateStep(prevWord: string, nextWord: string): Observable<string> {
    const requestOptions: Object = {
      responseType: 'text'
    };

    return this.http.get<string>(httpUrl + this.puzzleUrl + `/validateStep?prevWord=${prevWord}&nextWord=${nextWord}`, requestOptions);
  }

  generatePuzzle(): Observable<string> {
    return this.http.get<string>(httpUrl + this.puzzleUrl + '/generateNew');
  }

  attemptSolve(solution: string[]): Observable<boolean> {
    return this.http.post<boolean>(httpUrl + this.puzzleUrl + '/validate', solution);
  }

  fetchPriorSolution(): Observable<PriorPuzzleInfo> {
    return this.http.get<PriorPuzzleInfo>(httpUrl + this.puzzleUrl + '/previous');
  }
}
