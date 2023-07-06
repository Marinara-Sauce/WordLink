import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'WordStep';

  helpVisible: boolean = false;
  previousVisible: boolean = false;

  ngOnInit(): void {
    if (!window.localStorage.getItem("first")) {
      this.showHelp();
      window.localStorage.setItem("first", "false");
    }
  }

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
