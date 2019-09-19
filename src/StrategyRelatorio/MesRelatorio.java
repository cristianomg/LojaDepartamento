package StrategyRelatorio;

public enum MesRelatorio {
	JANEIRO {
		@Override
		public RelatorioMensal obterMes() {
			return new Janeiro();
		}
	},
	FEVEREIRO {
		@Override
		public RelatorioMensal obterMes() {
			return new Fevereiro();
		}
	},
	MARCO {
		@Override
		public RelatorioMensal obterMes() {
			return new Marco();
		}
	},
	ABRIL {
		@Override
		public RelatorioMensal obterMes() {
			// TODO Auto-generated method stub
			return new Abril();
		}
	},
	MAIO {
		@Override
		public RelatorioMensal obterMes() {
			// TODO Auto-generated method stub
			return new Maio();
		}
	},
	JUNHO {
		@Override
		public RelatorioMensal obterMes() {
			// TODO Auto-generated method stub
			return new Junho();
		}
	},
	JULHO {
		@Override
		public RelatorioMensal obterMes() {
			// TODO Auto-generated method stub
			return new Julho();
		}
	},
	AGOSTO {
		@Override
		public RelatorioMensal obterMes() {
			// TODO Auto-generated method stub
			return new Agosto();
		}
	},
	SETEMBRO {
		@Override
		public RelatorioMensal obterMes() {
			// TODO Auto-generated method stub
			return new Setembro();
		}
	},
	OUTUBRO {
		@Override
		public RelatorioMensal obterMes() {
			// TODO Auto-generated method stub
			return new Outubro();
		}
	},
	NOVEMBRO {
		@Override
		public RelatorioMensal obterMes() {
			// TODO Auto-generated method stub
			return new Novembro();
		}
	},
		DEZEMBRO {
			@Override
			public RelatorioMensal obterMes() {
				// TODO Auto-generated method stub
				return new Dezembro();
			}
		};
	
	public abstract RelatorioMensal obterMes();
}
