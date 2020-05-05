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
  Checkbox,
  Grid,
  form,
} = window['material-ui'];

const styles = theme => ({
    
  root: {
        textAlign: 'center',
    backgroundColor: '#FE6B8A',
    padding: theme.spacing.unit,
    paddingTop: theme.spacing.unit*6,
    paddingBottom: theme.spacing.unit *39,
    height: '100%',
    overflowX: 'auto'
    
  },margin: {
    margin: 0,
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
  paper: {
    marginTop: theme.spacing.unit * 8,
    display: 'flex',
    flexDirection: 'column',
    align:'center',
    alignItems: 'center',
    backgroundColor: '#e1bee7',
    padding: `${theme.spacing.unit * 2}px ${theme.spacing.unit * 3}px ${theme.spacing.unit * 3}px`,
    
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
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing.unit,
  }
});
class Indexx extends React.Component {

  render() {
   
    const { classes } = this.props;
    return (
      <main className={classes.root}>
       
      <Paper className={classes.paper}>
        
        <Typography component="h1" variant="h5">
          Se subira su archivo al servidor para poder crear preguntas con archivos
        </Typography>
        <br/> <br/>
        <form action = "Archivos" method = "post" encType = "multipart/form-data">
            <FormControl margin="normal" required fullWidth>
                <Input type = "file" name = "archivo" size = "45" />
            </FormControl>
        
          <Button
          
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
          >
            Subir
          </Button>
          

        </form>
        
                 <a href="tableQuestions.html" >
          <Button
          
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
          >
            Regresar
          </Button>
          </a>
      </Paper>
        
      
      </main>
    );
  }
}
const ServletUpload = withStyles(styles)(Indexx);
ReactDOM.render(<ServletUpload />, document.getElementById('root'));

