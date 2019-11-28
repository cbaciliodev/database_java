import { Component, OnInit } from '@angular/core';
import { Docente } from 'src/app/models/docente.model';
import { CargoService } from 'src/app/services/service.index';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert'
import { Asignado } from 'src/app/models/asignado.model';

@Component({
  selector: 'app-asignaciones-temas',
  templateUrl: './asignaciones-temas.component.html',
  styleUrls: ['./asignaciones-temas.component.css']
})
export class AsignacionesTemasComponent implements OnInit {

  public _listaDocente: Docente[];
  public _asignados: Asignado[];
  public cantidad: number;
  public p: number = 1;
  public paginador: any;

  actualizar = false;
  _habilitar = true;
  _check = false;

  constructor(public _serviceCargo: CargoService,
    public _router: Router,
    public _activateRouter: ActivatedRoute) {
    this._asignados = [];
  }

  ngOnInit() {
    this.findAllAsignado();
  }

  findAllAsignado() {
    this._serviceCargo.findAllAsignacion()
      .subscribe(res => {
        if (res == null) {
          this._asignados = [];
          return;
        }
        this._asignados = res;
  })
}

deleteAsignacion(idt: number, idd: number) {

  swal(`Una vez eliminado, no podrás recuperar este registro!`, {
    title: `Estas seguro?`,
    icon: `warning`,
    buttons: [`Cancel`, `Ok`],
    dangerMode: true
  })
    .then((willDelete) => {
      if (willDelete) {
        this._serviceCargo.deleteAsignacion(idt, idd)
          .subscribe(data => {

            if (data.mensaje == `The DELETE statement conflicted with the REFERENCE constraint "FK__TBL_REUNI__FK_IC__3E52440B". The conflict occurred in database "REUNION", table "dbo.TBL_REUNIONES", column 'FK_ICOD_DOCENTE'.`) {
              swal(`Lo siento, no de puede borrar`, `El registro esta relacionado con la Tabla Reuniones`, {
                icon: `error`,
              });
            } else if (data.mensaje == `El registro ha sido eliminado con éxito.`) {
              swal(`Buen Trabajo`, ` ${data.mensaje}`, {
                icon: `success`,
              });
            }
            this.findAllAsignado();
          });

      } else {
        swal(`Informacion`, `El registro no fue eliminado!`, `info`);
      }
    });
}
}
