
const {
  Button,
  Icon,
  Typography,
  Paper,
  withStyles,
  Grid,
  ButtonBase,
  Checkbox,
  BottomNavigation,
  BottomNavigationAction,Input,FormControl,InputLabel
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
  icon: {
    margin: theme.spacing.unit,
    fontSize: 32,
  },
  image: {
    position: 'center',
    maxHeight: 200,
    maxWidth: 500
  },
  main: {
    width: 'auto',
    display: 'block', // Fix IE 11 issue.
    marginLeft: theme.spacing.unit * 3,
    marginRight: theme.spacing.unit * 3,
    
    [theme.breakpoints.up(400 + theme.spacing.unit * 3 * 2)]: {
      width: 4000,
      marginLeft: 'auto',
      marginRight: 'auto',
           
    },
  },
  paper: {
    padding: theme.spacing.unit * 20,
    paddingTop: theme.spacing.unit * 2,
    paddingBottom: theme.spacing.unit * 4,
  

 //   margin: 'auto',
  //  minWidth: 50,
    height: '100%',
    backgroundColor: '#e1bee7',
    overflowX: 'auto'
    
  //  background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
   },margin: {
    margin: 0,

  },
  
  button: {
    margin: theme.spacing.unit,
  },
  submit: {
    marginTop: theme.spacing.unit * 3,
  },
  leftButton: {
    marginTop: theme.spacing.unit*2,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing.unit,
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    paddingTop: theme.spacing.unit * 1000,
    paddingBottom: theme.spacing.unit * 1,
    paddingLeft: theme.spacing.unit * 50,
    paddingRight: theme.spacing.unit * 50,
  },
});
class Index extends React.Component {
    
    constructor(props){
      super(props);
      this.state = {
          check:[],
          id: "",
          pregunta: "",
          respuestas:[],
          multimedia: "",
          tipo: "",
          anterior: "",
          siguiente: "",
          aciertos: 0,
          errores: 0,
          correctas: [],
          incorrec: 0,
          correc: 0
      }
    }
    
    tipodeMultimedia(tipo){
        const { classes } = this.props;
        console.log("Se llamó a la función, tipo es: "+tipo);
        if(tipo==="jpg" || tipo==="png"){
                return (<ButtonBase className={classes.image}> <img className={classes.img} src={this.state.multimedia} /> </ButtonBase>);
            }else if(tipo==="mp4"){
                return (<video controls> <source src={this.state.multimedia} type='video/mp4' /> </video>);
            }else if(tipo==="mp3"){
                return (<audio controls> <source src={this.state.multimedia} type='audio/mpeg' /> </audio>);
            }
    }
    
    handleChange = name => event => {
    this.setState({ [name]: event.target.checked });
    }
    
    handleToggle = value => () => {
    const { check } = this.state;
    const currentIndex = check.indexOf(value);
    const newChecked = [...check];

    if (currentIndex === -1) {
      newChecked.push(value);
    } else {
      newChecked.splice(currentIndex, 1);
    }

    this.setState({
      check: newChecked,
    });
    }
    
    evaluar= () => {
        var i = 0, c=0, e=0, inc=0, cor=0;
        var dato = this; 
        const { check,correctas,respuestas } = dato.state;
        console.log(check);
        for(i=0;i<check.length;i++){
            console.log("check["+i+"]: "+check[i]);
            console.log("respuestas.indexOf(check[i]): "+respuestas.indexOf(check[i]));
            console.log("correctas[respuestas.indexOf(check[i])]: "+correctas[respuestas.indexOf(check[i])]);
            if(correctas[respuestas.indexOf(check[i])]==="true"){
                c++;
            }
        }
        
        for(i=0;i<check.length;i++){
            if(correctas[respuestas.indexOf(check[i])]==="false"){
                e++;
            }
        }
        for(i=0;i<correctas.length;i++){
            if(correctas[i]==="false"){
                inc++;
            }
        }
        for(i=0;i<correctas.length;i++){
            if(correctas[i]==="true"){
                cor++;
            }
        }
        console.log("c: "+c+" e: "+e);
        dato.setState({
            aciertos: c,
            errores: e,
            incorrec: inc,
            correc: cor
        });
    };
    
    componentDidMount = () => {
      fetch("pregunta.json", {
          method: "GET"
      }).then(response => response.json())
        .then( json =>{
            console.log(json);
            var x = json.multimedia.substring(json.multimedia.length -3, json.multimedia.length);
            console.log(x);
            this.setState ({
                id: json.id,
                pregunta: json.pregunta,
                respuestas: json.resp,
                multimedia: json.multimedia,
                anterior: json.anterior,
                siguiente: json.siguiente,
                examen: json.examen,
                correctas: json.corr,
                tipo: x
      });return x;});
  }
  
  render() {
    const {classes} = this.props;
    return (
    <div>        
      <Paper className={classes.root}> 
      <Paper className={classes.paper}>


                <Typography gutterBottom variant="h6">
                  {this.state.id}
                </Typography>
                <Typography variant="h5">{this.state.pregunta}</Typography>
                <FormControl margin="normal"  fullWidth>
                <InputLabel >Escriba su respuesta* </InputLabel>
                <Input id="resp1" name="resp1" value = {this.state.resp1}/> 
                </FormControl>
                
                

          <Grid item justify="center">
              {this.tipodeMultimedia(this.state.tipo)}
          </Grid>
        <BottomNavigation
          onChange={this.handleChange}
          showLabels
          className={classes.paper}
        >
          <BottomNavigationAction label={'Anterior: "'+this.state.anterior+'"'}  onClick = {() => window.location.replace("verE?examen="+this.state.examen+"&pregunta="+this.state.anterior)} icon={ <Icon className={classes.icon}>reply</Icon>  } />
          <BottomNavigationAction label={'Siguiente: "'+this.state.siguiente+'"'} onClick = {() => window.location.replace("verE?examen="+this.state.examen+"&pregunta="+this.state.siguiente)} icon={<Icon className={classes.icon}>redo</Icon>} />
         
        </BottomNavigation>

                <a href="tableExam.html">
                 <Button fullWidth variant="contained" color= '#FFF' className={classes.leftButton}>Regresar</Button>
        </a>
      </Paper>
      </Paper>

      </div>
    );
  }
}
const VerExamen = withStyles(styles)(Index);
ReactDOM.render(<VerExamen />, document.getElementById('root'));
