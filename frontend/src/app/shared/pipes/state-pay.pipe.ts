import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'statePay',
})
export class StatePayPipe implements PipeTransform {
  transform(value: string): string {
    if (value === 'COMPLETED') {
      return 'Completado';
    } else {
      return 'Pendiente';
    }
  }
}
