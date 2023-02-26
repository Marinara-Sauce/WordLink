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

  fetchPuzzleInfo() {
    this.puzzleService.getPuzzleInfo().subscribe(puzzle => {
      this.startingWord = puzzle.startingWord;
      this.targetWord = puzzle.targetWord;
      this.bestSolve = puzzle.bestSolve;

      for (var i = 0 ; i < this.startingWord.length ; i++) {
        this.gap = this.gap + ' ';
      }

      this.steps.push(this.startingWord);
    });
  }

  onSubmitWord(form: NgForm) {
    this.puzzleService.validateStep(this.steps[this.steps.length - 1], form.value.word).subscribe(valid => {
      switch (valid) {
        case "BAD_SIZE":
          this.wordError = `Each word must be ${this.startingWord.length} characters long`;
          break;
        case "NOT_A_VALID_STEP":
          this.wordError = "You can only change one letter at a time";
          break;
        case "NOT_A_WORD":
          this.wordError = `${form.value.word} is not a valid word`;
          break;
        case "SOLUTION":
          this.solved = true;
          this.solvedModalOpen = true;
          this.wordError = '';
          this.steps.push(form.value.word);
          
          this.attemptSolve();
          break;
        default:
          this.wordError = '';
          this.steps.push(form.value.word);
      }

      form.reset();
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

  resetToWord = (word: string): void => {
    var indexOfWord = this.steps.indexOf(word);
    this.steps = this.steps.slice(0, indexOfWord + 1);
  }
}
