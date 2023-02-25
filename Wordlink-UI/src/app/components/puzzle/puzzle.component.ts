import { Component, OnInit } from '@angular/core';
import { PuzzleServiceService } from 'src/app/services/puzzle-service.service';

@Component({
  selector: 'app-puzzle',
  templateUrl: './puzzle.component.html',
  styleUrls: ['./puzzle.component.css']
})
export class PuzzleComponent implements OnInit {

  constructor(private puzzleService: PuzzleServiceService) { }

  ngOnInit(): void {
    this.fetchPuzzleInfo();
  }

  startingWord: string = '';
  targetWord: string = '';

  fetchPuzzleInfo() {
    this.puzzleService.getPuzzleInfo().subscribe(puzzle => {
      this.startingWord = puzzle.startingWord;
      this.targetWord = puzzle.targetWord;
    });
  }
}
