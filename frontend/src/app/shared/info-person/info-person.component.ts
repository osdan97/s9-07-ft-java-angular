import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-info-person',
  templateUrl: './info-person.component.html',
  styleUrls: ['./info-person.component.scss']
})
export class InfoPersonComponent {
  @Output() salida1 = new EventEmitter<boolean>();

  On_click1() {
    this.salida1.emit(true);
    console.log('true');
  }
}
