const {
  Button, colors,createMuiTheme,CssBaseline,Dialog,DialogActions,DialogContent,DialogContentText,
  DialogTitle,Icon,MuiThemeProvider,Typography,Paper,withStyles,main,Avatar,FormControl,
  InputLabel,Input,FormControlLabel,form, Checkbox, Grid, Switch } = window['material-ui'];

const styles = theme => ({
    
  root: {
   // textAlign: 'center',
    paddingTop: theme.spacing.unit * 20,
     backgroundColor: '#FE6B8A',
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
          resp1 : "",
          resp1C : "",
          resp2 : "",
          resp2C : "",
          resp3 : "",
          resp3C : "",
          resp4 : "",
          resp4C : "",
          resp5 : "",
          resp5C : "",
          resp6 : "",
          resp6C : "",
          resp7 : "",
          resp7C : "",
          resp8 : "",
          resp8C : "",
          tipo: "pattern",
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
          Nueva Pregunta
        </Typography>
        <form action = "WriteP" method = "post" className={classes.form} onSubmit={this.handleSubmit}>
          <input type='hidden' id='tipo' name='tipo' value='pattern' />
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
            <Grid container item xs={16} spacing={24} justify="center">
                <Grid item xs={8}>
                    <Typography variant="h6" gutterBottom>
                        PATTERN
                    </Typography>
                </Grid>
                <Grid item xs={4}>
                    <Typography variant="h6" gutterBottom>
                        PUNTAJE
                    </Typography>
                </Grid>
            </Grid>
            
            <Grid container item xs={16} spacing={24} justify="center" alignItems="flex-end">
                <Grid item xs={8}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Pattern 1</InputLabel>
                        <Input id="resp1" name="resp1" value = {this.state.resp1} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
                 <Grid item xs={4}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Puntaje 1</InputLabel>
                        <Input id="resp1C" name="resp1C" value = {this.state.resp1C} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
            </Grid>
            
            <Grid container item xs={16} spacing={24} justify="center" alignItems="flex-end">
                <Grid item xs={8}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Pattern 2</InputLabel>
                        <Input id="resp2" name="resp2" value = {this.state.resp2} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
                 <Grid item xs={4}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Puntaje 2</InputLabel>
                        <Input id="resp2C" name="resp2C" value = {this.state.resp2C} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
            </Grid>
            
            <Grid container item xs={16} spacing={24} justify="center" alignItems="flex-end">
                <Grid item xs={8}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Pattern 3</InputLabel>
                        <Input id="resp3" name="resp3" value = {this.state.resp3} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
                 <Grid item xs={4}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Puntaje 3</InputLabel>
                        <Input id="resp3C" name="resp3C" value = {this.state.resp3C} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
            </Grid>
            
            <Grid container item xs={16} spacing={24} justify="center" alignItems="flex-end">
                <Grid item xs={8}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Pattern 4</InputLabel>
                        <Input id="resp4" name="resp4" value = {this.state.resp4} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
                 <Grid item xs={4}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Puntaje 4</InputLabel>
                        <Input id="resp4C" name="resp4C" value = {this.state.resp4C} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
            </Grid>
            
            <Grid container item xs={16} spacing={24} justify="center" alignItems="flex-end">
                <Grid item xs={8}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Pattern 5</InputLabel>
                        <Input id="resp5" name="resp5" value = {this.state.resp5} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
                 <Grid item xs={4}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Puntaje 5</InputLabel>
                        <Input id="resp5C" name="resp5C" value = {this.state.resp5C} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
            </Grid>
            
            <Grid container item xs={16} spacing={24} justify="center" alignItems="flex-end">
                <Grid item xs={8}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Pattern 6</InputLabel>
                        <Input id="resp6" name="resp6" value = {this.state.resp6} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
                 <Grid item xs={4}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Puntaje 6</InputLabel>
                        <Input id="resp6C" name="resp6C" value = {this.state.resp6C} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
            </Grid>
            
            <Grid container item xs={16} spacing={24} justify="center" alignItems="flex-end">
                <Grid item xs={8}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Pattern 7</InputLabel>
                        <Input id="resp7" name="resp7" value = {this.state.resp7} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
                 <Grid item xs={4}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Puntaje 7</InputLabel>
                        <Input id="resp7C" name="resp7C" value = {this.state.resp7C} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
            </Grid>
            
            <Grid container item xs={16} spacing={24} justify="center" alignItems="flex-end">
                <Grid item xs={8}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Respuesta 8</InputLabel>
                        <Input id="resp8" name="resp8" value = {this.state.resp8} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
                 <Grid item xs={4}>
                    <FormControl margin="normal"  fullWidth>
                        <InputLabel >Puntaje 8</InputLabel>
                        <Input id="resp8C" name="resp8C" value = {this.state.resp8C} onChange = {this.handleInputChange}/>
                    </FormControl>
                </Grid>
            </Grid>


        </Grid>
         <br/>
         <br/>
         <Typography  gutterBottom>
          *Antes de seleccionar un archivo, asegurese de primero subirlo al servidor en la pagina principal*
          </Typography>
         <Input type = "file" name = "link" size = "45"/>
         <br/>
         <br/>
         
          <Button
          
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
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
const NewPregunta = withStyles(styles)(Index);
ReactDOM.render(<NewPregunta />, document.getElementById('root'));