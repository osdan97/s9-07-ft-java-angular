import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-our-services',
  templateUrl: './our-services.component.html',
  styleUrls: ['./our-services.component.scss'],
})
export class OurServicesComponent {
  servicesContent = signal([
    {
      icon: '../../../../../../assets/images/truck.jpg',
      title: 'Envios',
      description: 'Nuestros proveedores llegan dentro de toda la UE',
    },
    {
      icon: '../../../../../../assets/images/pay.jpg',
      title: 'Opciones de pago',
      description: 'Tenemos varias formas de pago',
    },
    {
      icon: '../../../../../../assets/images/like.jpg',
      title: 'Sitio de confianza',
      description: 'Protegemos tu privacidad',
    },
  ]);
}
