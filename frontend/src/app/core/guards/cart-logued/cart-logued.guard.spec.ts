import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { cartLoguedGuard } from './cart-logued.guard';

describe('cartLoguedGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => cartLoguedGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
