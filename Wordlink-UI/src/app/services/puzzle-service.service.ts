import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import PuzzleInfo from '../PuzzleInfo';
import { httpUrl } from 'src/environments/environment';

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
}
