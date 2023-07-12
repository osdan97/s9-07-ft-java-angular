import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartUnloggedComponent } from './cart-unlogged.component';

describe('FinCompraComponent', () => {
  let component: CartUnloggedComponent;
  let fixture: ComponentFixture<CartUnloggedComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CartUnloggedComponent]
    });
    fixture = TestBed.createComponent(CartUnloggedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
