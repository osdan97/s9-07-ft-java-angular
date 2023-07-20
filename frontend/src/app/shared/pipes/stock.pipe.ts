import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'stock',
})
export class StockPipe implements PipeTransform {
  transform(value: number): string {
    if (value > 0) {
      return 'EN STOCK';
    } else {
      return 'SIN STOCK';
    }
  }
}
