import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'WordLink';

  helpVisible: boolean = false;
  previousVisible: boolean = false;

  showHelp(): void {
    this.helpVisible = true;
  }

  hideHelp = (): void => {
    this.helpVisible = false;
  }

  showPrevious(): void {
    this.previousVisible = true;
  }

  hidePrevious = (): void => {
    this.previousVisible = false;
  }
}
