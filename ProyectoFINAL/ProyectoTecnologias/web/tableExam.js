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


class Index extends React.Component {
    constructor(props){
      super(props);
      this.state = {
          examen:[]
      }
  }

  componentDidMount = () => {
      fetch("examen.json", {
          method: "GET"
      }).then(response => response.json())
        .then( json =>{
            this.setState ({
            examen: json.ids
      })})
  }



  handleClick = () => {
    var script = document.createElement("script");
    script.src = 'NuevaP.js';
    script.type = 'text/babel';
    document.body.appendChild(script);
    console.log("jala");
  };
  
  eliminar = value => () =>{
    
    if (confirm('Estas seguro de borrar este examen?')) {
       window.location.replace("EliminarE?examenEliminado="+value)
    } 
  } 

  render() {
    const { classes } = this.props;
    return (
    <div>
    <Paper className={classes.paper} >
        <br/>
        <Typography component="h3" variant="h5">
        EXAMENES <Icon className={classes.icon} color="secondary">favorite</Icon>
        </Typography>
        <br /><br />
        
      <Paper>
      <Table className={classes.table}>
        <TableHead>
          <TableRow>
            <CustomTableCell>Examenes</CustomTableCell>
            <CustomTableCell>Opciones</CustomTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {this.state.examen.map(row => {
            return (
              <TableRow className={classes.row}>
                <CustomTableCell component="th" scope="row">
                  {row}
                </CustomTableCell>
                <CustomTableCell>
                    <IconButton aria-label="Evaluar Examen" className={classes.margin} color="secondary" onClick = {() => window.location.replace("verE?examen="+row)}>
                        <Icon className={classes.icon}>visibility</Icon>
                    </IconButton>
                    <IconButton aria-label="Editar Examen" className={classes.margin}  color="primary" onClick = {() => window.location.replace("EditarE?examen="+row)}>
                        <Icon className={classes.icon}>edit</Icon> 
                    </IconButton>
                    <IconButton  aria-label="Eliminar Examen" className={classes.margin} color="inherity" onClick = {this.eliminar(''+row)}>
                        <Icon className={classes.icon}>deleteicon</Icon>
                    </IconButton>
                </CustomTableCell>
              </TableRow>
            );

          })}
        </TableBody>
      </Table>
      </Paper>
           
         
         <MuiThemeProvider theme={theme}> 
        <a href ="NuevoEx">
        <Button position="right" className={classes.button2} >
             Nuevo Examen
        <Icon className={classes.icon}>add_circle</Icon>
        </Button>          
         </a>
         </MuiThemeProvider>         
         
         
        <MuiThemeProvider theme={theme}>
        <a href="tableQuestions.html">
        <Button position="right" className={classes.button2} >
             Tabla Preguntas
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

const TableExam= withStyles(styles)(Index);
ReactDOM.render(<TableExam />, document.getElementById('root'));
