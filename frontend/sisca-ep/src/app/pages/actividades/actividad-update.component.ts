import { Component, OnInit } from '@angular/core';
import { Actividad } from 'src/app/models/actividad.model';
import { CargoService } from 'src/app/services/service.index';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import swal from 'sweetalert'

@Component({
  selector: 'app-actividad-update',
  templateUrl: './actividad-update.component.html',
  styleUrls: ['./actividad-update.component.css']
})
export class ActividadUpdateComponent implements OnInit {

  public actividadUpdate = new Actividad(null, '', null);
  actualizar = false;
  _habilitar = true;
  _check = false;

  constructor(public _serviceCargo: CargoService,
    public _router: Router,
    public _activateRouter: ActivatedRoute) {

    _activateRouter.params.subscribe(params => {
      let id = params['id'];
      if (id !== 'nuevo') {
        this.actualizar = true;
        this.cargarActividad(id);
      }
    })

  }

  ngOnInit() {
  }

  cargarActividad(id: number) {
    this._serviceCargo.findOneActividadById(id)
      .subscribe(actividad => {
        this.actividadUpdate = actividad
      })
  };

  guardarActividad(f: NgForm, s: string) {

    if (f.invalid) {

      return;
    }

    if (s == 'create') {
      this._serviceCargo.createActividad(this.actividadUpdate)
        .subscribe(data => {
          swal(`Actividad Creada`, `${data.mensaje}`, `success`)
          this._router.navigate(['/actividad'])
        });
    }
    if (s == 'update') {
      this._serviceCargo.updateActividad(this.actividadUpdate, this.actividadUpdate.icod_ACTIVIDAD)
        .subscribe(data => {
          swal(`Actividad Actualizada`, `${data.mensaje}`, `success`)
          this._router.navigate(['/actividad'])
        });
    }
  }
}
