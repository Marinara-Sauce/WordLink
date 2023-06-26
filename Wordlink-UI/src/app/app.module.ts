import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HeadingComponent } from './components/heading/heading.component';
import { PuzzleComponent } from './components/puzzle/puzzle.component';
import { WordComponent } from './components/word/word.component';
import { LetterComponent } from './components/letter/letter.component';

import { FormsModule }   from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { ModalComponent } from './components/popup/modal.component';
import { PriorSolutionComponent } from './components/prior-solution/prior-solution.component';
import { KeyboardComponent } from './components/keyboard/keyboard.component';

@NgModule({
  declarations: [
    AppComponent,
    HeadingComponent,
    PuzzleComponent,
    WordComponent,
    LetterComponent,
    ModalComponent,
    PriorSolutionComponent,
    KeyboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
