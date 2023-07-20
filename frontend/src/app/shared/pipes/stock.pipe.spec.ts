import { StockPipe } from './stock.pipe';

describe('StockPipe', () => {
  it('create an instance', () => {
    const pipe = new StockPipe();
    expect(pipe).toBeTruthy();
  });
});
