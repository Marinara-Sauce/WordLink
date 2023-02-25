import { Component, Input, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-word',
  templateUrl: './word.component.html',
  styleUrls: ['./word.component.css']
})
export class WordComponent implements OnInit {

  @Input() word: string = '';

  letters: string[] = [];
  
  constructor() { }

  ngOnInit(): void {

  }

  ngOnChanges(): void {
    this.letters = Array.from(this.word);
  }

}
