import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { PuzzleServiceService } from 'src/app/services/puzzle-service.service';

@Component({
  selector: 'app-heading',
  templateUrl: './heading.component.html',
  styleUrls: ['./heading.component.css']
})
export class HeadingComponent implements OnInit {

  constructor(private puzzleService: PuzzleServiceService) { }

  @Output() showHelpEmitter = new EventEmitter<string>();
  @Output() showPreviousEmitter = new EventEmitter<string>();

  ngOnInit(): void {
  }

  generateNewPuzzle(): void {
    this.puzzleService.generatePuzzle().subscribe(() =>
      window.location.reload());
  }

  showHelp(): void {
    this.showHelpEmitter.emit();
  }

  showPrevious = (): void => {
    this.showPreviousEmitter.emit();
  }
}
