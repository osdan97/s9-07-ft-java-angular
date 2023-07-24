import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeArgentinaComponent } from './home-argentina.component';

describe('HomeArgentinaComponent', () => {
  let component: HomeArgentinaComponent;
  let fixture: ComponentFixture<HomeArgentinaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HomeArgentinaComponent]
    });
    fixture = TestBed.createComponent(HomeArgentinaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
