import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-keyboard',
  templateUrl: './keyboard.component.html',
  styleUrls: ['./keyboard.component.css']
})
export class KeyboardComponent {
  @Output() keyPress: EventEmitter<string> = new EventEmitter<string>();
  @Output() backspace: EventEmitter<void> = new EventEmitter<void>();
  @Output() enter: EventEmitter<void> = new EventEmitter<void>();

  onKeyPress(key: string) {
    this.keyPress.emit(key);
  }

  onBackspace() {
    this.backspace.emit();
  }

  onEnter() {
    this.enter.emit();
  }
}
