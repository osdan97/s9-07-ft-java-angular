import { NumberPhonePipe } from './number-phone.pipe';

describe('NumberPhonePipe', () => {
  it('create an instance', () => {
    const pipe = new NumberPhonePipe();
    expect(pipe).toBeTruthy();
  });
});
