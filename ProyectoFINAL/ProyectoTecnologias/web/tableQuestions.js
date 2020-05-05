const {
  Button,
  AppBar,
  Toolbar,
  Grid,
  Icon,
  IconButton,
  Paper,
  withStyles,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography,
  MuiThemeProvider,
  createMuiTheme,
  Fab,
  input, 
  label
} = window['material-ui'];

const CustomTableCell = withStyles(theme => ({
  head: {
    background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
    color: theme.palette.common.white,
  },
  body: {
    fontSize: 14,
  },
  
  button: {
    margin: theme.spacing.unit,
  },
}))(TableCell);


const theme = createMuiTheme({
  overrides: {
    // Name of the component ⚛️ / style sheet
    MuiButton: {
      // Name of the rule
      text: {
        // Some CSS
        background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
        borderRadius: 3,
        border: 0,
        color: 'white',
        height: 48,
        padding: '0 30px',
        boxShadow: '0 3px 5px 2px rgba(255, 105, 135, .3)',
      },
    },
    
  },
  typography: { useNextVariants: true },
});

const styles = theme => ({
  root: {
    backgroundColor: '#e1bee7', 
    padding: theme.spacing.unit * 2,
    paddingTop: theme.spacing.unit * 2,
    paddingBottom: theme.spacing.unit * 9,
    height: '100%',
     overflowX: 'auto'
  },margin: {
    margin: 0,

  },
  table: {
    minWidth: 700,
  },
  row: {
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.background.default,
    },
  },
  icon: {
    margin: theme.spacing.unit,
    fontSize: 32,
  },
  paper: {
    padding: theme.spacing.unit * 2,
    paddingTop: theme.spacing.unit * 2,
    paddingBottom: theme.spacing.unit * 4,
    height: '100%',
    backgroundColor: '#e1bee7',
    overflowX: 'auto'
  },margin: {
    margin: 0,
  },
  
  
  appBar: {
    top: 'auto',
    bottom: 0,
  },
  leftButton: {
    marginLeft: theme.spacing.unit *143,
  },
  rightButton:{
   marginLeft: theme.spacing.unit *140 ,
  },
  
  button:{
   margin: theme.spacing.unit,
  },
  
  button2:{
       margin: theme.spacing.unit*5,
  },
  
 
  
  
});



let id = 0;
function pregunta(nombre) {
  id += 1;
  return { id, nombre};
}

let numeroprueba=0;
function nextPage(){
    console.log(numeroprueba);
    if (numeroprueba === 0) {
        return numeroprueba
    }
    numeroprueba++;
    return numeroprueba;
}

const rows = [
  pregunta('Pregunta 1'),
  pregunta('Pregunta 2'),
  pregunta('Pregunta 3'),
  pregunta('Pregunta 4'),
  pregunta('Pregunta 5'),
];
class Index extends React.Component {
    constructor(props){
      super(props);
      this.state = {
          preguntas:[]
      }
  }
  
      
  leerjson(){
        var xhttp = new XMLHttpRequest();
        var dato = this; 
        xhttp.open("GET", "preguntas.json", true);
        xhttp.send();
        xhttp.onreadystatechange = function() {
            if(xhttp.readyState === 4 && xhttp.status===200)
            {
                var i,javaobj= JSON.parse(this.response);
                console.log(javaobj);
                console.log(javaobj.ids.length);
                for(i=0;i<javaobj.ids.length;i++){
                    console.log(javaobj.ids[i]);
                    dato.setState ({
                    preguntas: javaobj.ids
                });
                    console.log(dato.state.preguntas[i]);
                    console.log("logitud preguntas: "+dato.state.preguntas.length);
                }        
            }
        };      
    }
   eliminar = value => () =>{
    
    if (confirm('Estas seguro de borrar esta pregunta?')) {
       window.location.replace("EliminarP?preguntaEliminada="+value)
    } 
  } 
  
  componentDidMount = () => {
      fetch("preguntas.json", {
          method: "GET"
      }).then(response => response.json())
        .then( json =>{
            this.setState ({
            preguntas: json.ids
      })})
  }
  
  //() => window.location.replace("verExamen?pregunta=Pregunta%20"+1)
  
  
  render() {
    const { classes } = this.props;
    return (
    <div>
    <Paper className={classes.root} >
        <br/>
        <Typography component="h3" variant="h5">
        Bienvenido <Icon className={classes.icon} color="secondary">favorite</Icon>
        </Typography>
        <br /><br />
      <Paper>
      <Table className={classes.table}>
        <TableHead>
          <TableRow>
            <CustomTableCell>Lista de preguntas</CustomTableCell>
            <CustomTableCell>Opciones</CustomTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {this.state.preguntas.map(row => {
            return (
              <TableRow className={classes.row}>
                <CustomTableCell component="th" scope="row">
                  {row}
                </CustomTableCell>
                <CustomTableCell> 
                    <IconButton aria-label="Ver Pregunta" className={classes.margin} color="secondary" onClick = {() => window.location.replace("VistaP?pregunta="+row)}> 
                        <Icon className={classes.icon}>visibility</Icon> 
                    </IconButton>  
                    <IconButton aria-label="Editar Pregunta" className={classes.margin} color="primary" onClick = {() => window.location.replace("EditarP?pregunta="+row)}>  
                        <Icon className={classes.icon}>edit</Icon> 
                    </IconButton> 
                    <IconButton aria-label="Borrar Pregunta" className={classes.margin} color="inherity" onClick = {this.eliminar(''+row)}>  
                        <Icon className={classes.icon}>deleteicon</Icon> 
                    </IconButton> 
                </CustomTableCell>
              </TableRow>
            );
    
          })}
        </TableBody>
      </Table>
      </Paper>
      
      <a href="Archivos.html" style={{ color: '#FFF' }} >
            <Fab  aria-label="Subir"  className={classes.button}>
              <Icon>cloud_upload</Icon>
            </Fab>
          </a>         
         
         <MuiThemeProvider theme={theme}> 
        <a href ="verExamen">
        <Button position="right" className={classes.button2} >
            Examenes 
        <Icon className={classes.icon}>add_circle</Icon>
        </Button>          
         </a>
         </MuiThemeProvider>         
         
         
        <MuiThemeProvider theme={theme}>
        <a href="NuevaP.html">
        <Button position="right" className={classes.button2} >
            Nueva Pregunta 
        <Icon className={classes.icon}>add_circle</Icon>
        </Button>
        </a>
        </MuiThemeProvider>       
        <br /><br />
    </Paper>

    
     <AppBar position="fixed" color="secondary" className={classes.appBar}>
        <Toolbar>      
          
          <MuiThemeProvider theme={theme} >
          <a href="inicio.html" style={{ color: '#FFF' }}>
            <Button  className={classes.leftButton} >
            Logout
            </Button>
          </a>
          </MuiThemeProvider>
          </Toolbar>
        </AppBar>
    </div>
  );
  }
};

const MenuInicio = withStyles(styles)(Index);
ReactDOM.render(<MenuInicio />, document.getElementById('root'));