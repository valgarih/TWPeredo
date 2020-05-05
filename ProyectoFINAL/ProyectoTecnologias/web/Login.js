const {
  Button, colors,createMuiTheme,CssBaseline,Dialog,DialogActions,DialogContent,DialogContentText,
  DialogTitle,Icon,MuiThemeProvider,Typography,Paper,withStyles,main,Avatar,FormControl,
  InputLabel,Input,FormControlLabel,form, Checkbox } = window['material-ui'];

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
    background: 'linear-gradient(45deg, #4DD0E1 30%, #673ab7 90%)',
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
    marginRight: theme.spacing.unit*2,
  },
  main: {
      
    width: 'auto',
    display: 'block', // Fix IE 11 issue.
    marginLeft: theme.spacing.unit * 3,
    marginRight: theme.spacing.unit * 3,
    [theme.breakpoints.up(400 + theme.spacing.unit * 3 * 2)]: {
      width: 400,
      marginLeft: 'auto',
      marginRight: 'auto',
    },
  },
  
  avatar: {
    margin: theme.spacing.unit,
    backgroundColor: theme.palette.secondary.main,
    
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing.unit*2,
     display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
     paddingTop: theme.spacing.unit * 1,
    paddingBottom: theme.spacing.unit * 1,
    paddingLeft: theme.spacing.unit * 50,
    paddingRight: theme.spacing.unit * 50,
  },
  submit: {
    marginTop: theme.spacing.unit * 3,
  },  
});
class Index extends React.Component {
  constructor(props){
      super(props);
      this.state = {
          email : "",
          password : ""
      }
  }
  handleSubmit = event => {
      event.preventDefault;
      const data = this.state;
      console.log(data);
      
  }
  handleInputChange = (event) => {
       event.preventDefault;
       this.setState({
           [event.target.name] : event.target.value
       });
  }
  render() {
      
    const { email,password } = this.state;
    const { classes } = this.props;
    return (
            <div >
      
        <CssBaseline />
      <Paper className={classes.root}>
        <Avatar className={classes.avatar} >
            <Icon>favorite</Icon>
        </Avatar>
        
        <Typography component="h1" variant="h5">
          Iniciar Sesión
        </Typography>
        <form action = "LoginCorrectoIncorrecto" method = "post" className={classes.form} onSubmit={this.handleSubmit}>
          <FormControl margin="normal" required fullWidth>
            <InputLabel htmlFor="email">Correo Electrónico</InputLabel>
            <Input id="email" name="email" autoComplete="email" autoFocus value = {this.state.email} onChange = {this.handleInputChange}/>
          </FormControl>
          <FormControl margin="normal" required fullWidth>
            <InputLabel htmlFor="password"> Contraseña</InputLabel>
            <Input id="password" name="password" type="password" autoComplete="current-password" value = {this.state.password} onChange = {this.handleInputChange}/>
          </FormControl>
        
         <MuiThemeProvider theme={theme}>
          <Button 
          
            type="submit"
            fullWidth
            className={classes.submit}
            onClick={event => this.handleSubmit(event)}
          >
            Entrar
          </Button>
         </MuiThemeProvider>
        </form>
      </Paper>
      
      </div>
    );
  }
}
const Login = withStyles(styles)(Index);
ReactDOM.render(<Login />, document.getElementById('root'));