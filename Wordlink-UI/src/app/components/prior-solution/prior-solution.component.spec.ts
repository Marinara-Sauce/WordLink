import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PriorSolutionComponent } from './prior-solution.component';

describe('PriorSolutionComponent', () => {
  let component: PriorSolutionComponent;
  let fixture: ComponentFixture<PriorSolutionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PriorSolutionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PriorSolutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
