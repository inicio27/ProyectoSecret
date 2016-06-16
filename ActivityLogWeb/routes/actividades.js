var express = require('express');
var router = express.Router();

//Obtiene todas las actividades de la base de datos
//path /actividades
router.get("/",function(req, res, next){
  req.db.query("SELECT * FROM actividad",function(err, results, fields){
    if(err){
      res.send([]);
    }else{
      res.send(results);
        }
  });
});


//Obtiene todas las actividades de la base de datos segun un id
//path /actividades/:id
router.get("/:id", function(req, res, next){
  var id = req.params.id;
  req.db.query("SELECT * FROM actividad WHERE _idACT = "+id, function(err, results, fields){
    if(err || results.length == 0){
      //res.send([]);
      res.status(404).send({msj:"Actividad no encontrada"});
    }else{
      res.send(results[0]);
        }
  });
});


//Inserta una actividad en la base de datos
//path /actividades

router.post("/", function(req, res, next){
  var body = req.body;
  req.db.query("INSERT INTO actividad SET NomACT = ?, columFech = ?", [body.NomACT,body.columFech],function(err,results){
    if(err){
      res.send({success:false});
    }else{
      res.send({successfull:true});
    }
  });
});



//Actualiza una actividad en la base de datos
//path /actividades/:id
router.put("/:id", function(req , res , next){
  var id = req.params.id;
  var body = req.body;
  req.db.query("UPDATE actividad SET NomACT = ?, columFech = ? WHERE id = ?",[body.NomACT, body.columFech, id], function(err, results){
    if(){
      res.send({success:false});
    }else{
      res.send({success:true})
    }
  });
});



//Elimina una actividad en la base de datos
//path /actividades/:id
router.delete("/:id", function(req, res, next){
  var id = req.params.id;
  req.db.query("DELETE FROM actividad WHERE _idACT = " + id, function(err, results){
    if(err){
      res.send({success:false});
    }else{
      res.send({successfull:true});
    }
  });
});


module.exports = router;
