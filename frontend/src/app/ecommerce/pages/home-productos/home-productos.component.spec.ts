import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeProductosComponent } from './home-productos.component';

describe('HomeProductosComponent', () => {
  let component: HomeProductosComponent;
  let fixture: ComponentFixture<HomeProductosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HomeProductosComponent]
    });
    fixture = TestBed.createComponent(HomeProductosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
