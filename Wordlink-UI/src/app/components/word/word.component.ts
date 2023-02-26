import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-word',
  templateUrl: './word.component.html',
  styleUrls: ['./word.component.css']
})
export class WordComponent implements OnInit {

  @Input() word: string = '';
  @Input() resetFunction: ((word: string) => void) | undefined;

  letters: string[] = [];
  
  constructor() { }

  ngOnInit(): void {

  }

  ngOnChanges(): void {
    this.letters = Array.from(this.word);
  }

  onReset(): void {
    this.resetFunction && this.resetFunction(this.word);
  }

}
