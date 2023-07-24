import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { renewSessionGuard } from './renew-session.guard';

describe('renewSessionGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => renewSessionGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
