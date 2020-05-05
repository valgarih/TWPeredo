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
  Grid
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
    paddingBottom: theme.spacing.unit * 30,
    paddingLeft: theme.spacing.unit * 5,
    paddingRight: theme.spacing.unit * 5,
  },
  icon: {
    marginRight: theme.spacing.unit,
  },
  main: {
    width: 'auto',
    display: 'block', // Fix IE 11 issue.
    marginLeft: theme.spacing.unit * 3,
    marginRight: theme.spacing.unit * 3,
    [theme.breakpoints.up(400 + theme.spacing.unit * 3 * 2)]: {
      width: 490,
      marginLeft: 'auto',
      marginRight: 'auto',
    },
  },
 
  bigAvatar: {
    margin: theme.spacing.unit,
    backgroundColor: theme.palette.secondary.main,
     width: 40,
    height: 40,
    marginBottom: 40
    
  },

  submit: {
    marginTop: theme.spacing.unit * 7,
    marginBottom: theme.spacing.unit * 6,
  },  
});
class Indexx extends React.Component {
  constructor(props){
      super(props);
      this.state = {
          email : "",
          password : ""
      }
  }
  handleSubmit = event => {
      event.preventDefault;
      window.location.replace("inicio.html");
      
  }
  render() {
      
    const { email,password } = this.state;
    const { classes } = this.props;
    return (
      <div>
        <CssBaseline />
       
      <Paper className={classes.root}>
        
        
        <Typography component="h1" variant="h5">
          Usuario no registrado 
        </Typography>
        <Avatar className={classes.bigAvatar}>
            <Icon  fontSize="large">cancel</Icon>
        </Avatar>
        <MuiThemeProvider theme={theme}>
          <Button
          
            type="submit"
            
            className={classes.submit}
            onClick={event => this.handleSubmit(event)}
            
          >
            Inicio
          </Button>
       </MuiThemeProvider>
      </Paper>
      
      </div>
    );
  }
}
const LoginIncorrecto = withStyles(styles)(Indexx);
ReactDOM.render(<LoginIncorrecto />, document.getElementById('root'));