const {
  Button, colors,createMuiTheme,CssBaseline,Dialog,DialogActions,DialogContent,DialogContentText,
  DialogTitle,Icon,MuiThemeProvider,Typography,Paper,withStyles,main,Avatar,FormControl,
  InputLabel,Input,FormControlLabel,form, Checkbox, Grid, Switch } = window['material-ui'];

const styles = theme => ({
    
  root: {
   textAlign: 'center',
     backgroundColor: '#FE6B8A',
   // textAlign: 'center',
    paddingTop: theme.spacing.unit * 8,
    paddingRight: theme.spacing.unit * 10,
    paddingLeft: theme.spacing.unit * 10,
    height: '100%',
    overflowX: 'auto',
    display: 'block',
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
      width: 400,
      marginLeft: 'auto',
      marginRight: 'auto',
    },
  },
  paper: {
    marginTop: theme.spacing.unit * 8,
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    height: '100%',
    padding: `${theme.spacing.unit * 2}px ${theme.spacing.unit * 3}px ${theme.spacing.unit * 3}px`,
    overflowX: 'auto',
    
    backgroundColor: '#e1bee7'
    
  },
  avatar: {
    margin: theme.spacing.unit,
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing.unit,
  },
  submit: {
    marginTop: theme.spacing.unit * 3,
  },  
});
class Index extends React.Component {
  constructor(props){
      super(props);
      this.state = {
          idpregunta : "",
          texto : "",
        //  resp1 : "",
          resp1C: false,
          tipo: "FV",
          archivo:""
          
      }
  }
  
  handleChange = name => event => {
    this.setState({ [name]: event.target.checked });
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
    const { classes } = this.props;
    return (
      <div>        
      <main className={classes.root}>
        <CssBaseline />
      <Paper className={classes.paper}>
        <Avatar className={classes.avatar}>
            <Icon>check_box</Icon>
        </Avatar>
        
        <Typography component="h1" variant="h5">
          Pregunta Falso Verdadero
        </Typography>
        <form action = "WriteP" method = "post" className={classes.form} onSubmit={this.handleSubmit}>
          <input type='hidden' id='tipo' name='tipo' value='FV' />
          <FormControl margin="normal" required fullWidth>
            <InputLabel>Id de Pregunta</InputLabel>
            <Input id="id" name="idpregunta" autoFocus value = {this.state.idpregunta} onChange = {this.handleInputChange}/>
          </FormControl>
          <FormControl margin="normal" required fullWidth>
            <InputLabel >Texto de la Pregunta</InputLabel>
            <Input id="texto" name="texto" value = {this.state.texto} onChange = {this.handleInputChange}/>
          </FormControl>
         <br/>
         <br/>
        <Grid container spacing={4}>
                    <Typography variant="h6" gutterBottom>
                       Seleccione su respuesta
                    </Typography>
            
            <Grid container item xs={16} spacing={24} justify="center" alignItems="flex-end">

                <Grid item xs={4}>
                    <FormControlLabel
                        control={
                        <Switch
                            checked={this.state.resp1C}
                            onChange={this.handleChange('resp1C')}
                            value={this.state.resp1C}
                            inputProps={{name: "resp1C"}}
                        />
                        }
                    label={this.state.resp1C.toString()}
                    />
                </Grid>
            </Grid>
            

        </Grid>
         <br/>
         <br/>
         <Input type = "file" name = "link" size = "45"/>
         <br/>
         <br/>
         <Button
          
            type="submit"
            fullWidth
            variant="contained"
            color="secondary"
            className={classes.submit}
            onClick={event => this.handleSubmit(event)}
         >
            Agregar
         </Button>
        
        
        <a href="tableQuestions.html">
            <Button fullWidth variant="outlined" color="black">Regresar</Button>
        </a>
        </form>
      </Paper>
      </main>
       </div> 
    );
  }
}

const NuevaPFV = withStyles(styles)(Index);
ReactDOM.render(<NuevaPFV/>, document.getElementById('root'));

