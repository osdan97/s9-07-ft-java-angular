import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoCasaComponent } from './info-casa.component';

describe('InfoCasaComponent', () => {
  let component: InfoCasaComponent;
  let fixture: ComponentFixture<InfoCasaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InfoCasaComponent]
    });
    fixture = TestBed.createComponent(InfoCasaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
