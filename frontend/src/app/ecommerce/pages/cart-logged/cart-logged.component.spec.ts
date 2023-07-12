import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartLoggedComponent } from './cart-logged.component';

describe('CartLoggedComponent', () => {
  let component: CartLoggedComponent;
  let fixture: ComponentFixture<CartLoggedComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CartLoggedComponent]
    });
    fixture = TestBed.createComponent(CartLoggedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
