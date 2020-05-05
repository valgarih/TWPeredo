const {
  Avatar,
  Button,
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
  Input,
  FormControl,
  InputLabel,
  Checkbox,
  FormControlLabel, colors,createMuiTheme,CssBaseline,Dialog,DialogActions,DialogContent,DialogContentText,
  DialogTitle,MuiThemeProvider,form, Switch
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
          preguntas:[],
          check:[],
          idexamen : ""
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
  fetch("EditarExamen.json", {
          method: "GET"
      }).then(response => response.json())
        .then( json =>{
            this.setState ({
           
            idexamen: json.idExamen
      })})
  }

  handleChange = name => event => {
    this.setState({ [name]: event.target.checked });
  };
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
           
    <Paper className={classes.root}>
        <br/>
        <center>
         <Avatar className={classes.icon}>
            <Icon color="secondary">border_color</Icon>
        </Avatar>
        </center>
       
        <Typography component="h1" variant="h5">
        <center>Editar examen</center>
        </Typography>
        <br /> <br/>
        <form action = "WriteE" method = "post" className={classes.form} onSubmit={this.handleSubmit}>
          <input type='hidden' id='tipo' name='tipo' />
          <FormControl margin="normal" required fullWidth>
            <InputLabel>Id de Examen</InputLabel>
            <Input id="id" name="idexamen" autoFocus value = {this.state.idexamen} onChange = {this.handleInputChange}/>
          </FormControl>
         <br/>
      <Paper>
      <Table className={classes.table} >
        <TableHead>
          <TableRow>
            <CustomTableCell>Pregunta</CustomTableCell>
            <CustomTableCell>Seleccion de Preguntas</CustomTableCell>
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
                   <FormControlLabel
                    control={
                        <Checkbox                  
                            checked={this.state.check[row]}
                            name="NombrePregunta"
                            
                            value={row}
                            
                        />
                     }
                    />
                    <IconButton aria-label="Ver Pregunta" className={classes.margin} color="secondary" onClick = {() => window.location.replace("VistaP2?pregunta="+row+"&examen="+this.state.idexamen)}> 
                        <Icon className={classes.icon}>visibility</Icon> 
                    </IconButton> 
                  
                </CustomTableCell>
              </TableRow>
            );
    
          })}
        </TableBody>
      </Table>
      </Paper>
      <br/>
      <Button
          
            type="submit"
            fullWidth
            variant="contained"
            color="secondary"
            className={classes.submit}
            onClick={event => this.handleSubmit(event)}
         >
            Guardar
       </Button>
     
       <a href="tableExam.html">
            <Button fullWidth  color="secondary">Regresar</Button>
        </a>
    </form>
    </Paper>
    </div>
  );
  }
}

const EditarExamen = withStyles(styles)(Index);
ReactDOM.render (<EditarExamen/>, document.getElementById('root'));

