import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarCategoriesComponent } from './navbar-categories.component';

describe('NavbarCategoriesComponent', () => {
  let component: NavbarCategoriesComponent;
  let fixture: ComponentFixture<NavbarCategoriesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NavbarCategoriesComponent]
    });
    fixture = TestBed.createComponent(NavbarCategoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
