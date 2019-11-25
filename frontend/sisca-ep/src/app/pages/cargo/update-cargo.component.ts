import { Component, OnInit } from '@angular/core';
import { Cargo } from 'src/app/models/cargo.model';
import { CargoService } from 'src/app/services/service.index';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import swal from 'sweetalert'

@Component({
  selector: 'app-update-cargo',
  templateUrl: './update-cargo.component.html',
  styleUrls: ['./update-cargo.component.css']
})
export class UpdateCargoComponent implements OnInit {

  public cargoUpdate = new Cargo(null, '');
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
        this.cargarCargo(id);
      }
    })

  }

  ngOnInit() {
  }

  cargarCargo(id: number) {
    this._serviceCargo.findOneById(id)
      .subscribe(cargo => {
        this.cargoUpdate = cargo
      })
  };


  guardarCargo(f: NgForm, s: string) {

    if (f.invalid) {

      return;
    }

    if (s == 'create') {
      this._serviceCargo.createCargo(this.cargoUpdate)
        .subscribe(data => {
          swal(`Cargo Creado`, `${data.mensaje}`, `success`)
          this._router.navigate(['/cargo'])
        });
    }
    if (s == 'update') {
      this._serviceCargo.updateCargo(this.cargoUpdate, this.cargoUpdate.icod_CARGO)
        .subscribe(data => {
          swal(`Cargo Actualizado`, `${data.mensaje}`, `success`)
          this._router.navigate(['/cargo'])
        });
    }
  }
}