import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { cartUnloguedGuard } from './cart-unlogued.guard';

describe('cartUnloguedGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => cartUnloguedGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
