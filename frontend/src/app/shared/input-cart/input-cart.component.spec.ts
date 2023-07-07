import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InputCartComponent } from './input-cart.component';

describe('InputCartComponent', () => {
  let component: InputCartComponent;
  let fixture: ComponentFixture<InputCartComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InputCartComponent]
    });
    fixture = TestBed.createComponent(InputCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
