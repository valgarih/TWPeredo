const {
  Button,
  colors,
  createMuiTheme,
  CssBaseline,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Icon,
  MuiThemeProvider,
  Typography,
  Paper,
  withStyles,
  main,
  Avatar,
  FormControl,
  InputLabel,
  Input,
  FormControlLabel,
  form,  
  Checkbox,
  green,
  Grow,
} = window['material-ui'];

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
   ...theme.mixins.gutters(),
     
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    paddingTop: theme.spacing.unit * 20,
    paddingBottom: theme.spacing.unit * 20,
    paddingLeft: theme.spacing.unit * 5,
    paddingRight: theme.spacing.unit * 5,
  },
  icon: {
    marginRight: theme.spacing.unit,
  },

  bigAvatar: {
    margin: theme.spacing.unit,
    width: 40,
    height: 40,
    marginBottom: 40,
    
  },

  submit: {
    marginTop: theme.spacing.unit * 7,
    marginBottom: theme.spacing.unit * 6,
  },  
});

class Index extends React.Component {
  constructor(props){
      super(props);
      this.state = {
          nombre : "",
          id : ""
      }
     
      
  }
  
  
  
  
  leerjson(){
        var xhttp = new XMLHttpRequest();
        var dato = this; 
        xhttp.open("GET", "datos.json", true);
        xhttp.send();
        xhttp.onreadystatechange = function() {
            if(xhttp.readyState === 4 && xhttp.status===200)
            {
                var javaobj= JSON.parse(this.response);
                dato.setState ({
                    nombre:javaobj.nombre,
                    id : javaobj.id
                });          
            }
        };      
    }
    
    componentDidMount(){
        fetch("datos.json", {
          method: "GET"
      })
        .then(response => response.json())
        .then(json => {
            console.log(json);
                this.setState ({
                    nombre:json.nombre,
                    id : json.id
                });
      });
    }
    
   handleSubmit = event => {
      event.preventDefault;
      window.location.replace("tableQuestions.html");
      
  } 


  render() {    
    var { nombre,id } = this.state;
    const { classes } = this.props;
    
    const mensajeBienvenida = (
        <Typography component="h1" variant="h5">
         Bienvenido/a :  {this.state.nombre}
        </Typography>
    ); 
    const mensajeExito = (
        <Typography component="h1" variant="h5">
           ¡LOGIN EXITOSO!  
        </Typography>
    );    
    const icono =(
        <Avatar className={classes.bigAvatar} style={{backgroundColor: '#4CAF50'}}>
            <Icon  fontSize="large">check-circle-outline</Icon>
        </Avatar>
    );
    const boton =(
            <MuiThemeProvider theme={theme}>
            <Button
          
            type="submit"
            className={classes.submit}
          >
            Comencemos
          </Button>
          </MuiThemeProvider>
    );
    const contenedor =(
        <Paper className={classes.root}>
          
            <Grow
                in={true}
                style={{ transformOrigin: "0 0 0" }}
                 {...(true ? { timeout: 800 } : {})}
                >
                {mensajeExito}
            </Grow>
            <Grow
                in={true}
                style={{ transformOrigin: "0 0 0" }}
                 {...(true ? { timeout: 700 } : {})}
                >
                {mensajeBienvenida}
            </Grow>
            <Grow
                in={true}
                style={{ transformOrigin: "0 0 0" }}
                 {...(true ? { timeout: 400 } : {})}
                >
                {icono}
            </Grow>
            <Grow
                in={true}
                style={{ transformOrigin: "0 0 0" }}
                 {...(true ? { timeout: 1200 } : {})}
                >
                <form action="readQuestions" method = "GET">{boton}</form>
                
            </Grow>
 
        </Paper>
    );
    
    return (
         <div>
            <CssBaseline />
            {contenedor}
        </div>
      
    );
  }
}
const LoginCorrecto = withStyles(styles)(Index);
ReactDOM.render(<LoginCorrecto />, document.getElementById('root'));