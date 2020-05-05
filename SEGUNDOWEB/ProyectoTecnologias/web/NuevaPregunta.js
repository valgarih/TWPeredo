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

  render() {
    const { classes } = this.props;
    return (
            <div>    
      <Paper className={classes.root}>    
        <Typography component="h1" variant="h5">
          Crear una pregunta
        </Typography>
         <br/>
        <MuiThemeProvider theme={theme}>
        <a href="NuevaPFV.html">
            <Button  className={classes.button} size="large">
            Pregunta Tipo Falso/Verdadero
            <Icon className={classes.icon}>view_module</Icon>
            </Button>
        </a>
        </MuiThemeProvider>
        <br/>
         <MuiThemeProvider theme={theme}>
        <a href="NuevaP.html">
            <Button  className={classes.button} size="large">
            Pregunta Tipo Pattern
            <Icon className={classes.icon}>check_box</Icon>
            </Button>
        </a>
        </MuiThemeProvider>
         <br/>
         <MuiThemeProvider theme={theme}>
         <a href="tableQuestions.html">
            <Button  className={classes.button2} size="large">
            Regresar
            <Icon className={classes.icon}>arrow_back</Icon>
            </Button>
        </a>
        </MuiThemeProvider>
        
        
        
      </Paper>
      </div>  
    );
  }
}

const NuevaPregunta = withStyles(styles)(Index);
ReactDOM.render(<NuevaPregunta/>, document.getElementById('root'));





