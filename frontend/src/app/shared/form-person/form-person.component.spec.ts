import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPersonComponent } from './form-person.component';

describe('FormPersonComponent', () => {
  let component: FormPersonComponent;
  let fixture: ComponentFixture<FormPersonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormPersonComponent]
    });
    fixture = TestBed.createComponent(FormPersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
