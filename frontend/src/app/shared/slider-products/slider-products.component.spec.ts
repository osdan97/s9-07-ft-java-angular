import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SliderProductsComponent } from './slider-products.component';

describe('SliderProductsComponent', () => {
  let component: SliderProductsComponent;
  let fixture: ComponentFixture<SliderProductsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SliderProductsComponent]
    });
    fixture = TestBed.createComponent(SliderProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
