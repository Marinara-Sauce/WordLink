import { Component, OnInit } from '@angular/core';
import { PuzzleServiceService } from 'src/app/services/puzzle-service.service';

@Component({
  selector: 'app-heading',
  templateUrl: './heading.component.html',
  styleUrls: ['./heading.component.css']
})
export class HeadingComponent implements OnInit {

  constructor(private puzzleService: PuzzleServiceService) { }

  ngOnInit(): void {
  }

  generateNewPuzzle(): void {
    this.puzzleService.generatePuzzle().subscribe(() =>
      window.location.reload());
  }

}
