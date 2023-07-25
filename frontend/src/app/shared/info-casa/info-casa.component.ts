import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-info-casa',
  templateUrl: './info-casa.component.html',
  styleUrls: ['./info-casa.component.scss']
})
export class InfoCasaComponent  {
  @Output() salida = new EventEmitter<boolean>();

  On_click() {
    this.salida.emit(true);
    console.log('true');
  }
}
