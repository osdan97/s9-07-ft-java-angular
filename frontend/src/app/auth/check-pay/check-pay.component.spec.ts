import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckPayComponent } from './check-pay.component';

describe('CheckPayComponent', () => {
  let component: CheckPayComponent;
  let fixture: ComponentFixture<CheckPayComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CheckPayComponent]
    });
    fixture = TestBed.createComponent(CheckPayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
