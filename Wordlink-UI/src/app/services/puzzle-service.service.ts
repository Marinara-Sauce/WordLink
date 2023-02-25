import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PuzzleServiceService {

  private puzzleUrl = '/puzzle';

  constructor(private http: HttpClient) { }

  


}
