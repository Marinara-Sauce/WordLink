import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
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
  bestSolve: number = 0;

  gap: string = '';

  steps: string[] = [];

  wordError: string = '';

  solved: boolean = false;
  solvedModalOpen: boolean = false;

  currentLetters: string = '____';

  fetchPuzzleInfo() {
    this.puzzleService.getPuzzleInfo().subscribe(puzzle => {
      this.startingWord = puzzle.start;
      this.targetWord = puzzle.target;
      this.bestSolve = puzzle.bestSolve;

      for (var i = 0 ; i < this.startingWord.length ; i++) {
        this.gap = this.gap + ' ';
      }

      this.steps.push(this.startingWord);
    });
  }

  onSubmitWord() {
    this.puzzleService.validateStep(this.steps[this.steps.length - 1], this.currentLetters).subscribe(valid => {
      switch (valid) {
        case "BAD_SIZE":
          this.wordError = `Each word must be ${this.startingWord.length} characters long`;
          break;
        case "NOT_A_VALID_STEP":
          this.wordError = "You can only change one letter at a time";
          break;
        case "NOT_A_WORD":
          this.wordError = `${this.currentLetters} is not a valid word`;
          break;
        case "SOLUTION":
          this.solved = true;
          this.solvedModalOpen = true;
          this.wordError = '';
          this.steps.push(this.currentLetters);
          
          this.attemptSolve();
          break;
        default:
          this.wordError = '';
          this.steps.push(this.currentLetters);
      }

      this.currentLetters = '____';
    });
  }

  closeSolvedModal = (): void => {
    this.solvedModalOpen = false;
  }

  attemptSolve(): void {
    this.steps.push(this.targetWord);

    this.puzzleService.attemptSolve(this.steps).subscribe(solved => {
      this.steps = this.steps.slice(0, this.steps.length - 1);
      solved ? console.log('You solved the puzzle!') : console.log('Bad solution :(');
    });
  }

  onKeyEntered = (key: string): void => {
    for (var i = 0 ; i < this.currentLetters.length ; i++) {
      if (this.currentLetters[i] == '_') {
        this.currentLetters = this.currentLetters.substring(0, i) + key + this.currentLetters.substring(i, this.currentLetters.length - 1);
        break;
      }
    }
  }

  onBackspace = (): void => {
    for (var i = this.currentLetters.length - 1 ; i >= 0 ; i--) {
      if (this.currentLetters[i] != '_') {
        this.currentLetters = this.currentLetters.substring(0, i) + '_' + this.currentLetters.substring(i + 1, this.currentLetters.length);
        break;
      }
    }
  }

  resetToWord = (word: string): void => {
    var indexOfWord = this.steps.indexOf(word);
    this.steps = this.steps.slice(0, indexOfWord + 1);
  }
}
