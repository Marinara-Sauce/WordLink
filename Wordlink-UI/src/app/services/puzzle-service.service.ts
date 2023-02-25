import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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


}
