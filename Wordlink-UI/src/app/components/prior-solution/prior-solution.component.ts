import { Component, OnInit } from '@angular/core';
import { PuzzleServiceService } from 'src/app/services/puzzle-service.service';
import { PriorPuzzleInfo } from '../PriorPuzzleInfo';

@Component({
  selector: 'app-prior-solution',
  templateUrl: './prior-solution.component.html',
  styleUrls: ['./prior-solution.component.css']
})
export class PriorSolutionComponent implements OnInit {

  constructor(private puzzleService: PuzzleServiceService) { 
    this.viewPreviousSolution()
  }

  ngOnInit(): void {
  }

  modalOpen: boolean = false;

  priorSolution: PriorPuzzleInfo = {
    solution: [],
    bestSolve: 0,
    avgSteps: 0,
    numSolves: 0
  };

  viewPreviousSolution(): void {
    this.puzzleService.fetchPriorSolution().subscribe(sol => {
      this.priorSolution = sol;
    });
  }

  openModal(): void {
    this.modalOpen = true;
  }

  onCloseModal = (): void => {
    this.modalOpen = false;
  }

}
