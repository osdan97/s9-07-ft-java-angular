import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormCasaComponent } from './form-casa.component';

describe('FormCasaComponent', () => {
  let component: FormCasaComponent;
  let fixture: ComponentFixture<FormCasaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormCasaComponent]
    });
    fixture = TestBed.createComponent(FormCasaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
