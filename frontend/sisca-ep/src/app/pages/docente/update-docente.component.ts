import { Component, OnInit } from '@angular/core';
import { Docente } from 'src/app/models/docente.model';
import { Cargo } from 'src/app/models/cargo.model';
import { CargoService } from 'src/app/services/service.index';
import { NgForm } from '@angular/forms';
import swal from 'sweetalert'
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-docente',
  templateUrl: './update-docente.component.html',
  styleUrls: ['./update-docente.component.css']
})
export class UpdateDocenteComponent implements OnInit {

  public _cargo = new Cargo(null, '');
  public _docente = new Docente(null, '', '', '', this._cargo);

  public _lista_cargos: Cargo[];
  actualizar = false;
  _habilitar = true;
  _check = false;

  constructor(public _serviceCargo: CargoService,
    public _router: Router,
    public _activateRouter: ActivatedRoute) {
    this._lista_cargos = [];

    _activateRouter.params.subscribe(params => {
      let id = params['id'];
      if (id !== 'nuevo') {
        this.actualizar = true;
        this.cargarDocente(id);
      }
    })

  }

  ngOnInit() {
    this.cargarListaCargo();
  }


  valor(s: any) {
    console.log(s.target.value, ' valor')
  }
  cargarDocente(id: number) {

    this._serviceCargo.findOneDocenteById(id)
      .subscribe(docente => {
        this._docente = docente
      })
  };

  cargarListaCargo() {
    this._serviceCargo.findAllCargos()
      .subscribe(cargo => {
        this._lista_cargos = cargo
      })
  };



  guardarDocente(f: NgForm, s: string) {

    if (f.invalid) {
      return;
    }

    if (s == 'create') {

      this._docente.cargo.icod_CARGO = f.value.vdesc_CARGO

      this._serviceCargo.createDocente(this._docente)
        .subscribe(data => {
          swal(`Docente Creado`, `${data.mensaje}`, `success`)
          this._router.navigate(['/docente'])
        });
    }

    if (s == 'update') {
      this._serviceCargo.updateDocente(this._docente, this._docente.icod_DOCENTE)
        .subscribe(data => {
          swal(`Docente Actualizado`, `${data.mensaje}`, `success`)
          this._router.navigate(['/docente'])
        });
    }
  }
}
