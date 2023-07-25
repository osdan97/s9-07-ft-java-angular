import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeCountriesComponent } from './home-countries.component';

describe('HomeCountriesComponent', () => {
  let component: HomeCountriesComponent;
  let fixture: ComponentFixture<HomeCountriesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HomeCountriesComponent]
    });
    fixture = TestBed.createComponent(HomeCountriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
