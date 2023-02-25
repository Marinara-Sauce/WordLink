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

  steps: string[] = [];

  wordError: string = '';

  fetchPuzzleInfo() {
    this.puzzleService.getPuzzleInfo().subscribe(puzzle => {
      this.startingWord = puzzle.startingWord;
      this.targetWord = puzzle.targetWord;

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
        default:
          this.wordError = '';
          this.steps.push(form.value.word);
      }
    });
  }
}
